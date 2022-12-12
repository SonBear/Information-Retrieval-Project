import { FacetGroup } from '../FacetGroup';
import { useFacet } from '../../../../lib/hooks/useFacet';

export const FacetPanel = () => {
  const { facets } = useFacet();

  if (!facets) {
    return null;
  }

  return (
    <div>
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
    </div>
  );
};
