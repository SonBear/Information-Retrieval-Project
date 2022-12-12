export interface RetrievedDocument {
  id: string;
  title?: string;
  url?: string;
  language: string;
  textEnglish?: string;
  textSpanish?: string;
  contentType: string;
  score: number;
  highlightSnippets: string[];
}
