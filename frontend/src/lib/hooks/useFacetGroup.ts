import { useSearchParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { FacetItem } from '../../models/search/facet/FacetItem';
import { joinByComma, splitByComma } from '../../utils/arrays';

export const useFacetGroup = (facetGroupName: string) => {
  const [searchParams, setSearchParams] = useSearchParams();
  const [selectedClassifiers, setSelectedClassifiers] = useState<string[]>([]);

  useEffect(() => {
    const facetItemParams = searchParams.get(facetGroupName);
    if (facetItemParams) {
      setSelectedClassifiers(splitByComma(facetItemParams));
    }
  }, []);

  useEffect(() => {
    if (selectedClassifiers.length === 0) {
      searchParams.delete(facetGroupName);
    } else {
      searchParams.set(facetGroupName, joinByComma(selectedClassifiers));
    }
    setSearchParams(searchParams);
  }, [selectedClassifiers]);

  const handleFacetItemStateChanged = (
    facetItem: FacetItem,
    newState: boolean,
  ) => {
    if (newState) {
      setSelectedClassifiers(prev => [...prev, facetItem.classifier]);
    } else {
      setSelectedClassifiers(prev =>
        prev.filter(classifier => classifier !== facetItem.classifier),
      );
    }
  };

  const isFacetItemSelected = (facetItem: FacetItem) => {
    return Boolean(
      selectedClassifiers.find(selected => selected === facetItem.classifier),
    );
  };

  return {
    isFacetItemSelected,
    handleFacetItemStateChanged,
  };
};
