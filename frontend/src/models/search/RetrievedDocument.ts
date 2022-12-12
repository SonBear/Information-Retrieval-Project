export interface RetrievedDocument {
  title?: string;
  url?: string;
  language: string;
  textEnglish?: string;
  textSpanish?: string;
  contentType: string;
  score: number;
  highlightSnippets?: Array<string>;
}
