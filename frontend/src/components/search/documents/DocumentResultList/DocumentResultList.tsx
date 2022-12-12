import { RetrievedDocumentCard } from '../RetrievedDocumentCard';
import { RetrievedDocument } from '../../../../models/search/documents/RetrievedDocument';
import { Stack } from 'react-bootstrap';

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
    return <div className="display-4">No results :(</div>;
  }

  return (
    <Stack gap={3}>
      <div className="fs-4">Total results: {documents.length}</div>
      {documents.map((doc, index) => (
        <RetrievedDocumentCard key={doc.id} document={doc} />
      ))}
    </Stack>
  );
};
