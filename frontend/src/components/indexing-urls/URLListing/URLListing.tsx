import React, { useState } from 'react';
import {
  joinByBreakLine,
  removeBlankOrNullElements,
  splitByBreakLine,
} from '../../../utils/arrays';

export interface URLListProps {
  urls: string[];
  onUrlListChanged?: (newUrls: string[]) => void;
}

export const URLListing = ({
  urls = [],
  onUrlListChanged = () => {},
}: URLListProps) => {
  const [textValue, setTextValue] = useState(joinByBreakLine(urls));

  const onTextFieldChanged = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    let newValue = e.target.value;
    onUrlListChanged(removeBlankOrNullElements(splitByBreakLine(newValue)));
    setTextValue(newValue);
  };

  return (
    <textarea
      value={textValue}
      onChange={onTextFieldChanged}
      rows={30}
      cols={100}></textarea>
  );
};
