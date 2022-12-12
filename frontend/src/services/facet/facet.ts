import { api } from '../http';
import { SupportedFacetResult } from '../../models/search/facet/SupportedFacetResult';
import { appendQueryParams } from '../../utils/query-params';

export const getFacets = (queryParams: string) => {
  return api.get<SupportedFacetResult>(
    appendQueryParams('/facade/facet', queryParams),
  );
};
