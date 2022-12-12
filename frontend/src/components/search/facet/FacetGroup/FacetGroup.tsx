import { FacetItem } from '../../../../models/search/facet/FacetItem';
import { SelectableFacetItem } from '../SelectableFacetItem';
import { useFacetGroup } from '../../../../lib/hooks/useFacetGroup';
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
  const { isFacetItemSelected, handleFacetItemStateChanged } =
    useFacetGroup(facetGroupName);

  if (!facetItems || facetItems?.length === 0) {
    return null;
  }

  const sortFacetItemsByClassifier = () => {
    return facetItems.sort((a, b) => a.classifier.localeCompare(b.classifier));
  };

  return (
    <Stack>
      <p className="fw-bold fs-5">{facetGroupLabel}</p>
      <Form>
        {sortFacetItemsByClassifier().map((facetItem, index) => (
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
