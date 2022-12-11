import React, { useState } from 'react';
import { useNavigate, useSearchParams } from 'react-router-dom';

export const SearchPanel = () => {
  const navigate = useNavigate();
  const [searchParams] = useSearchParams();
  const [query, setQuery] = useState(searchParams.get('query') || '');

  const onQueryChanged = (e: React.ChangeEvent<HTMLInputElement>) => {
    const newValue = e.target.value;
    setQuery(newValue);
  };

  const onSearchClicked = () => {
    navigate(`/?query=${query}`);
  };

  return (
    <div>
      <input
        placeholder="Type something..."
        value={query}
        onChange={onQueryChanged}
      />
      <button onClick={onSearchClicked}>Search</button>
    </div>
  );
};
