import React from 'react';
import { Then, When } from 'react-if';
import { useIndexingDocument } from '../../../lib/hooks/useIndexingDocument';
import { FileArrowUp } from 'react-bootstrap-icons';
import { Button } from 'react-bootstrap';

export const IndexingDocumentPanel = () => {
  const { filesInputRef, filesToUpload, handleFilesChosen, handleUploadFiles } =
    useIndexingDocument();

  const onFilesChosen = (e: React.ChangeEvent<HTMLInputElement>) => {
    const chosenFiles = Array.prototype.slice.call(e.target.files) as File[];
    handleFilesChosen(chosenFiles);
  };

  const handleClick = (event: any) => {
    filesInputRef.current?.click();
  };

  return (
    <div className="">
      <Button className="btn btn-light" onClick={handleClick}>
        Select files
      </Button>
      <input
        className="btn btn-light"
        ref={filesInputRef}
        type="file"
        multiple
        onChange={onFilesChosen}
        style={{ display: 'none' }}
      />
      &nbsp;
      <When condition={filesToUpload.length > 0}>
        <Then>
          <span className="text-white">
            {filesToUpload.map(a => a.name + '; ')}
          </span>
          <button className="btn btn-light" onClick={handleUploadFiles}>
            Upload Files &nbsp;
            <FileArrowUp />
          </button>
        </Then>
      </When>
    </div>
  );
};
