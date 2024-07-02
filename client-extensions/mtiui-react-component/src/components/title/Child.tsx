interface Props {
  title: string
  troll: string
}
function Title({title, troll}: Props) {
  return (
    <div>
      <h1>Child do teste - {title}</h1>
      <p>By:{troll}</p>
    </div>
  )
}
export default Title