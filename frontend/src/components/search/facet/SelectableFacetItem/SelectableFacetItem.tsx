import { FacetItem } from '../../../../models/search/facet/FacetItem';
import React, { useEffect, useState } from 'react';
import { Form } from 'react-bootstrap';

export interface SelectableFacetItemProps {
  facetItem: FacetItem;
  selected?: boolean;
  onStateChanged?: (facetItem: FacetItem, newState: boolean) => void;
}

export const SelectableFacetItem = ({
  facetItem,
  selected = false,
  onStateChanged = () => {},
}: SelectableFacetItemProps) => {
  const [checked, setChecked] = useState(false);

  useEffect(() => {
    setChecked(selected);
  }, [selected]);

  const onCheckboxClicked = (e: React.ChangeEvent<HTMLInputElement>) => {
    let newState = e.target.checked;
    onStateChanged(facetItem, newState);
    setChecked(newState);
  };

  return (
    <Form.Check
      role="button"
      checked={checked}
      id={facetItem.classifier}
      label={`${facetItem.classifier} (${facetItem.count})`}
      onChange={onCheckboxClicked}
    />
  );
};
