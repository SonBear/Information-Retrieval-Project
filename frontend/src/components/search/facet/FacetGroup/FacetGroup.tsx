import { FacetItem } from '../../../../models/search/facet/FacetItem';
import { SelectableFacetItem } from '../SelectableFacetItem';
import { useFacetGroup } from '../../../../lib/hooks/facet/useFacetGroup';
import { Form, Stack } from 'react-bootstrap';

export interface FacetGroupProps {
  facetGroupLabel: string;
  facetGroupName: string;
  facetItems?: FacetItem[];
}

export const FacetGroup = ({
  facetGroupLabel,
  facetGroupName,
  facetItems = [],
}: FacetGroupProps) => {
  const { sortedFacetItems, isFacetItemSelected, handleFacetItemStateChanged } =
    useFacetGroup(facetItems, facetGroupName);

  if (!facetItems || facetItems?.length === 0) {
    return null;
  }

  return (
    <Stack>
      <p className="fw-bold fs-5">{facetGroupLabel}</p>
      <Form>
        {sortedFacetItems.map((facetItem, index) => (
          <SelectableFacetItem
            key={index}
            facetItem={facetItem}
            selected={isFacetItemSelected(facetItem)}
            onStateChanged={handleFacetItemStateChanged}
          />
        ))}
      </Form>
    </Stack>
  );
};
