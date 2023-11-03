import NoteBackground from "./NoteBackground";

export default function Layout({ children }) {
  return (
    <div>
      <NoteBackground />
      {children}
    </div>
  );
}
