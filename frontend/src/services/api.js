export default async function Fetch(url) {
  fetch("https://dev.cozytrain.com/api/" + url, { method: "POST" });
}
