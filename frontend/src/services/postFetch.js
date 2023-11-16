import getAccessToken from "@/utils/getAccessToken";

/**
 *
 * @param {string} url - URL
 * @param {Object} data - Body
 * @returns {Promise}
 */
export default async function fetchPost(url, data, type = "application/json") {
  const accessToken = getAccessToken();

  try {
    const response = await fetch(process.env.NEXT_PUBLIC_API + url, {
      method: "POST",
      body: JSON.stringify(data),
      headers: {
        "Content-Type": type,
        Authorization: `Bearer ${accessToken}`,
      },
    });

    if (response.status === 401) {
      const responseData = await response.json();

      const accessToken = responseData.error.message;
      document.cookie = `accessToken=${accessToken}; path=/`;

      return fetchPost(url, data, type);
    }

    if (!response.ok && response.status !== 401)
      throw new Error(`HTTP error! Status: ${response.status}`);

    const responseData = await response.json();
    return responseData;
  } catch (error) {
    throw error;
  }
}
