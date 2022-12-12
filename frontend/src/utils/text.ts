import { RetrievedDocument } from '../models/search/documents/RetrievedDocument';

export const appendEllipsisToFrontAndBack = (text: string) => {
  return `... ${text} ...`;
};

export const extractFirst35Chars = (text: string) => {
  return extractFirstNChars(text, 35);
};

export const extractFirst250Chars = (text: string) => {
  return extractFirstNChars(text, 250);
};

const extractFirstNChars = (text: string, nChars: number) => {
  let ellipse = '...';
  if (text.length <= nChars) {
    ellipse = '';
  }
  return text.slice(0, nChars) + ellipse;
};

export const extractText = (document: RetrievedDocument) => {
  if (document.textSpanish) {
    return extractFirst250Chars(document.textSpanish);
  }
  if (document.textEnglish) {
    return extractFirst250Chars(document.textEnglish);
  }
  return '';
};
