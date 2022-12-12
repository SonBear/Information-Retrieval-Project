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
    return (
      <Card bg="dark" text="white" key="Dark" className="mb-2">
        <Card.Header>{'titulo'}</Card.Header>
        <Card.Body>
          <Card.Title>
            <a href="www.google.com"> soy un rutl</a>
          </Card.Title>
          <Card.Text>{'testo del documento'}</Card.Text>
        </Card.Body>
      </Card>
    );
  }

  return (
    <div>
      <Card>
        <Card.Header>{document.title}</Card.Header>
        <Card.Body>
          <Card.Title>{document.url} </Card.Title>
          <Card.Text>
            {document.language == 'es'
              ? document.textSpanish
              : document.textEnglish}
          </Card.Text>
        </Card.Body>
      </Card>
    </div>
  );
};
