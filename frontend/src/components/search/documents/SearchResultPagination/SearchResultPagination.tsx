import { SearchResult } from '../../../../models/search/documents/SearchResult';
import { usePagination } from '../../../../lib/hooks/search/usePagination';
import { Pagination, Stack } from 'react-bootstrap';
import { useSearchParams } from 'react-router-dom';
import React, { useEffect, useState } from 'react';
import { useHomeQueryParams } from '../../../../lib/hooks/useHomeQueryParams';
import { ROWS_PER_PAGE } from '../../../../utils/pagination';

export interface SearchResultPaginationProps {
  searchResult: SearchResult;
}

export const SearchResultPagination = ({
  searchResult,
}: SearchResultPaginationProps) => {
  const { setQueryParam } = useHomeQueryParams();
  const [searchParams] = useSearchParams();
  const [currentPage, setCurrentPage] = useState(1);
  const { pagination, totalPages } = usePagination({
    totalItems: searchResult.totalDocsFound,
    pageSize: ROWS_PER_PAGE,
    currentPage: 1,
    pagesToDisplay: 5,
  });

  useEffect(() => {
    const page = searchParams.get('page') || '1';
    setCurrentPage(parseInt(page));
  }, [searchParams]);

  const onPageItemClicked = (page: string) => {
    if (page !== '...') {
      onChangePage(parseInt(page));
    }
  };

  const onChangePage = (page: number) => {
    setQueryParam('page', page.toString());
  };

  if (totalPages <= 1) {
    return null;
  }

  return (
    <Stack direction="horizontal" className="justify-content-center">
      <Pagination className="mt-3">
        <Pagination.First onClick={() => onChangePage(1)} />
        <Pagination.Prev
          onClick={() => onChangePage(Math.max(1, currentPage - 1))}
        />
        {pagination.map((page, index) => (
          <Pagination.Item
            key={index}
            active={index + 1 == currentPage}
            onClick={() => onPageItemClicked(page)}>
            {page}
          </Pagination.Item>
        ))}
        <Pagination.Next
          onClick={() => onChangePage(Math.min(currentPage + 1, totalPages))}
        />
        <Pagination.Last onClick={() => onChangePage(totalPages)} />
      </Pagination>
    </Stack>
  );
};
