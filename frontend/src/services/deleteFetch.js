import getAccessToken from "@/utils/getAccessToken";

/**
 *
 * @param {string} url - URL
 * @returns {Promise}
 */
export default async function Fetch(url) {
  const accessToken = getAccessToken();

  try {
    const response = await fetch(`${process.env.NEXT_PUBLIC_API}${url}`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${accessToken}`,
      },
    });

    if (response.status === 401) {
      const responseData = await response.json();

      const accessToken = responseData.error.message;
      document.cookie = `accessToken=${accessToken}; path=/`;

      return Fetch(url);
    }

    if (!response.ok && response.status !== 401)
      throw new Error(`HTTP error! Status: ${response.status}`);

    const responseData = await response.json();
    return responseData;
  } catch (error) {
    throw error;
  }
}
