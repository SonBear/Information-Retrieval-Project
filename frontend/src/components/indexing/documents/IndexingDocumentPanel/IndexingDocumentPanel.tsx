import React from 'react';
import { Then, When } from 'react-if';
import { useIndexingDocuments } from '../../../../lib/hooks/indexing/useIndexingDocuments';
import { Button, Stack } from 'react-bootstrap';

export const IndexingDocumentPanel = () => {
  const {
    filesInputRef,
    filesToUpload,
    handleFilesChosen,
    handleUploadFiles,
    handleClearChosenFiles,
  } = useIndexingDocuments();

  const onFilesChosen = (e: React.ChangeEvent<HTMLInputElement>) => {
    const chosenFiles = Array.prototype.slice.call(e.target.files) as File[];
    handleFilesChosen(chosenFiles);
  };

  return (
    <div>
      <div className="mb-3">
        <input
          ref={filesInputRef}
          className="form-control"
          type="file"
          multiple
          onChange={onFilesChosen}
        />
      </div>
      <When condition={filesToUpload.length > 0}>
        <Then>
          <Stack direction="horizontal" gap={2} className="justify-content-end">
            <Button variant="outline-secondary" onClick={handleUploadFiles}>
              Index documents
            </Button>
            <Button variant="outline-danger" onClick={handleClearChosenFiles}>
              Clear
            </Button>
          </Stack>
        </Then>
      </When>
    </div>
  );
};
