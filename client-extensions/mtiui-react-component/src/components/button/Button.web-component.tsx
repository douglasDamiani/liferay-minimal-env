import Button from './Button';
import { createRoot, Root } from 'react-dom/client';
class ButtonWebComponent extends HTMLElement {
    static get observedAttributes() {
        return ['value']
    }
    mountPoint!: HTMLSpanElement;
    root!: Root;
    render() {
        const title = this.getAttribute('value') || 'default';
        if (!this.root) this.root = createRoot(this.mountPoint);
        this.root.render(<Button value={title} />);
    }
    connectedCallback() {
        this.mountPoint = document.createElement('span');
        this.attachShadow({ mode: 'open' }).appendChild(this.mountPoint);

        this.render();
    }
    attributeChangedCallback() {
        this.render();
    }
}
export default ButtonWebComponent;
customElements.define('webcompoent-button', ButtonWebComponent);