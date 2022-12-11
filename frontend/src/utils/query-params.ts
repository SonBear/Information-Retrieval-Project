export const appendQueryParams = (baseUrl: string, queryParams: string) => {
  return appendToUrl(baseUrl, queryParams, '?');
};

const appendToUrl = (url: string, queryParams: string, prefix: string) => {
  if (queryParams) {
    return url + `${prefix}${queryParams}`;
  }
  return url;
};
