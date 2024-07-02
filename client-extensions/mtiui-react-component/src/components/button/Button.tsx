interface Props {
    value: string
}
function Title({ value }: Props) {
    return (

        <div className="border border-primary">
            <p>{value}</p>
        </div>

    )
}
export default Title