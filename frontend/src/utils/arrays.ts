export const joinByBreakLine = (urls: string[]) => {
  return urls.join('\r\n');
};

export const splitByBreakLine = (text: string) => {
  return text.split('\n');
};

export const removeBlankOrNullElements = (elements: string[]) => {
  return elements.filter(element => element && element.trim());
};
