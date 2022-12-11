import { RetrievedDocument } from '../../../../models/search/documents/RetrievedDocument';

export interface DocumentResultProps {
  document: RetrievedDocument;
}

export const DocumentResult = ({ document }: DocumentResultProps) => {
  return <pre>{JSON.stringify(document, null, 2)}</pre>;
};