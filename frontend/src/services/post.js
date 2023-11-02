import useStore from "@/store/useStore";

export default async function Fetch(url, data) {
  const { accessToken } = useStore();

  try {
    const response = await fetch("https://dev.cozytrain.com/api/" + url, {
      method: "POST",
      body: JSON.stringify(data),
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${accessToken}`,
      },
    });

    if (!response.ok) throw new Error(`HTTP error! Status: ${response.status}`);

    const responseData = await response.json();
    return responseData;
  } catch (error) {
    console.error("Fetch error:", error);
    throw error;
  }
}
