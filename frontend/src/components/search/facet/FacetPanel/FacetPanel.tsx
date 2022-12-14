import { FacetGroup } from '../FacetGroup';
import { useFacet } from '../../../../lib/hooks/facet/useFacet';
import { Stack } from 'react-bootstrap';

export const FacetPanel = () => {
  const { facets } = useFacet();

  if (!facets) {
    return null;
  }

  return (
    <Stack gap={3}>
      <FacetGroup
        facetGroupLabel="Document types"
        facetGroupName="documentType"
        facetItems={facets.documentType}
      />
      <FacetGroup
        facetGroupLabel="Languages"
        facetGroupName="language"
        facetItems={facets.language}
      />
    </Stack>
  );
};
