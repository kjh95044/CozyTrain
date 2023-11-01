export default function Layout(props) {
  return (
    <div>
      반갑습니다
      <div>{props.children}</div>
    </div>
  );
}
