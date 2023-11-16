import getAccessToken from "@/utils/getAccessToken";

/**
 *
 * @param {string} url - URL
 * @param {Object} data - Body
 * @returns {Promise}
 */
export default async function multipartFetchPost(url, data) {
  const accessToken = getAccessToken();

  try {
    const response = await fetch("https://dev.cozytrain.com/api/" + url, {
      method: "POST",
      body: data,
      headers: {
        Authorization: `Bearer ${accessToken}`,
      },
    });

    if (response.status === 401) {
      const responseData = await response.json();

      const accessToken = responseData.error.message;
      document.cookie = `accessToken=${accessToken}; path=/`;

      return multipartFetchPost(url, data);
    }

    if (!response.ok && response.status !== 401)
      throw new Error(`HTTP error! Status: ${response.status}`);

    const responseData = await response.json();
    return responseData;
  } catch (error) {
    throw error;
  }
}
