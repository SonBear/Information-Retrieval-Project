import { useSearchParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { SupportedFacetResult } from '../../../models/search/facet/SupportedFacetResult';
import { getFacets } from '../../../services/facet/facet';

export const useFacet = () => {
  const [searchParams] = useSearchParams();
  const [facets, setFacets] = useState<SupportedFacetResult>();

  useEffect(() => {
    const queryParams = searchParams.toString();
    getFacets(queryParams).then(result => {
      setFacets(result);
    });
  }, [searchParams]);

  return { facets };
};
