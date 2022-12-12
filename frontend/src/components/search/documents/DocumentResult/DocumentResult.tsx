import { doc } from 'prettier';
import { Link } from 'react-router-dom';
import { RetrievedDocument } from '../../../../models/search/RetrievedDocument';

export interface DocumentResultProps {
  document: RetrievedDocument;
}

export const DocumentResult = ({ document }: DocumentResultProps) => {
  return (
    <div className="mb-4 mt-5">
      <div className="text-size-14 font-normal blockquote-footer">
        {document.url}
      </div>
      <div style={{ fontSize: '23px' }}>
        <a target="_blank" href={document.url}>
          {document.title}
        </a>
      </div>
      <p
        dangerouslySetInnerHTML={{
          __html: document.highlightSnippets
            ? document.highlightSnippets.toString()
            : '',
        }}></p>
      <i>{'score: ' + document.score}</i>
      <br />
      <i>{'type: ' + document.contentType}</i>
    </div>
  );
};
