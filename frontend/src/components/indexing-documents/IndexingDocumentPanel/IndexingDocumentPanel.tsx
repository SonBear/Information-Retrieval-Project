import React from 'react';
import { Then, When } from 'react-if';
import { useIndexingDocument } from '../../../lib/hooks/useIndexingDocument';

export const IndexingDocumentPanel = () => {
  const { filesInputRef, filesToUpload, handleFilesChosen, handleUploadFiles } =
    useIndexingDocument();

  const onFilesChosen = (e: React.ChangeEvent<HTMLInputElement>) => {
    const chosenFiles = Array.prototype.slice.call(e.target.files) as File[];
    handleFilesChosen(chosenFiles);
  };

  return (
    <div>
      <input
        ref={filesInputRef}
        type="file"
        multiple
        onChange={onFilesChosen}
      />
      <When condition={filesToUpload.length > 0}>
        <Then>
          <button onClick={handleUploadFiles}>Upload selected files</button>
        </Then>
      </When>
    </div>
  );
};
