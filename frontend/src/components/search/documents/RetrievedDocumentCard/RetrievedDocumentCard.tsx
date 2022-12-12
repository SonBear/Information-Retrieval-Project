import { RetrievedDocument } from '../../../../models/search/documents/RetrievedDocument';
import { Card, Stack } from 'react-bootstrap';
import {
  appendEllipsisToFrontAndBack,
  extractFirst35Chars,
  extractText,
} from '../../../../utils/text';
import { Else, If, Then, When } from 'react-if';

interface DocumentAdditionalInfoTagProps {
  label: string;
  value: string | number;
}

const DocumentAdditionalInfoTag = ({
  label,
  value,
}: DocumentAdditionalInfoTagProps) => {
  return (
    <div>
      <span className="me-2 fw-bold">{label}:</span>
      <span>{value}</span>
    </div>
  );
};

export interface DocumentResultProps {
  document: RetrievedDocument;
}

export const RetrievedDocumentCard = ({ document }: DocumentResultProps) => {
  return (
    <Card>
      <When condition={document.title}>
        <Card.Header>{document.title}</Card.Header>
      </When>
      <Card.Body>
        <If condition={document.url}>
          <Then>
            <Card.Title>
              <a
                role="button"
                className="link-primary"
                href={document.url}
                target="_blank">
                {document.url}
              </a>
            </Card.Title>
          </Then>
          <Else>
            <Card.Title>{extractText(document)}</Card.Title>
          </Else>
        </If>
        <Card.Text>
          {document.highlightSnippets.map((hlSnippet, index) => (
            <div
              key={index}
              className="mb-2"
              dangerouslySetInnerHTML={{
                __html: appendEllipsisToFrontAndBack(hlSnippet),
              }}></div>
          ))}
        </Card.Text>
        <hr />
        <Stack direction="horizontal" gap={4} className="justify-content-end">
          <DocumentAdditionalInfoTag label="Score" value={document.score} />
          <DocumentAdditionalInfoTag
            label="Content type"
            value={extractFirst35Chars(document.contentType)}
          />
          <DocumentAdditionalInfoTag
            label="Language"
            value={document.language}
          />
        </Stack>
      </Card.Body>
    </Card>
  );
};
