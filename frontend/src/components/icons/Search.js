import Image from "next/image";

import search from "#/icons/search24.png";

export default function Search({ onClick }) {
  return <Image src={search} alt="검색" onClick={onClick}></Image>;
}
