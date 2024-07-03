interface Props {
    value: string
}
function Button({ value }: Props) {
    return (

        <div className="border border-primary">
            <p>{value}</p>
        </div>

    )
}
export default Button