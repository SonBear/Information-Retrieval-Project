import { RetrievedDocument } from '../../../../models/search/documents/RetrievedDocument';
import { DocumentResult } from '../DocumentResult';

export interface DocumentResultListProps {
  loading?: boolean;
  documents?: RetrievedDocument[];
}

export const DocumentResultList = ({
  loading = false,
  documents,
}: DocumentResultListProps) => {
  if (loading) {
    return <div>Loading...</div>;
  }

  if (!documents) {
    return null;
  }

  if (documents.length === 0) {
    return <div>No results :(</div>;
  }

  return (
    <div>
      {documents.map((doc, index) => (
        <DocumentResult key={index} document={doc} />
      ))}
    </div>
  );
};
