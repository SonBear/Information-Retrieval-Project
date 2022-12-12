import { RetrievedDocument } from '../../../../models/search/RetrievedDocument';
import Card from 'react-bootstrap/Card';
export interface FirstDocumentPanelProps {
  loading?: boolean;
  document?: RetrievedDocument;
}

export const FirstDocumentPanel = ({
  loading,
  document,
}: FirstDocumentPanelProps) => {
  if (loading) {
    return <div>Loading...</div>;
  }

  if (!document) {
    return null;
  }

  return (
    <div>
      <Card bg="dark" text="white" key="Dark" className="mb-2">
        <Card.Header>{document.title}</Card.Header>
        <Card.Body>
          <Card.Title>{document.url} </Card.Title>
          <Card.Text>
            {document.language == 'es'
              ? document.textSpanish?.slice(0, 250) + '....'
              : document.textEnglish?.slice(0, 250) + '....'}
          </Card.Text>
        </Card.Body>
      </Card>
    </div>
  );
};
