import NoteBackground from "../../components/NoteBackground";

export default function Layout({ children }) {
  return (
    <div>
      <NoteBackground />
      {children}
    </div>
  );
}
