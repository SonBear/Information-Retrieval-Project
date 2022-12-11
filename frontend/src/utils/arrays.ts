export const joinByBreakLine = (elements: string[]) => {
  return elements.join('\r\n');
};

export const joinByComma = (elements: string[]) => {
  return elements.join(',');
};

export const splitByBreakLine = (text: string) => {
  return text.split('\n');
};

export const splitByComma = (text: string) => {
  return text.split(',');
};

export const removeBlankOrNullElements = (elements: string[]) => {
  return elements.filter(element => element && element.trim());
};
