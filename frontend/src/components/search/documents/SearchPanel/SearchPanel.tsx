import React, { useEffect, useState } from 'react';
import { useNavigate, useSearchParams } from 'react-router-dom';
import { appendQueryParams } from '../../../../utils/query-params';
import logo from '../../../../logo.png';

export const SearchPanel = () => {
  const navigate = useNavigate();
  const [searchParams] = useSearchParams();
  const [query, setQuery] = useState('');

  useEffect(() => {
    const q = searchParams.get('query') || '';
    setQuery(q);
  }, [searchParams]);

  const onQueryChanged = (e: React.ChangeEvent<HTMLInputElement>) => {
    const newValue = e.target.value;
    setQuery(newValue);
  };

  const onSearchClicked = () => {
    searchParams.set('query', query);
    navigate(appendQueryParams('/', searchParams.toString()));
  };

  const inputStyle = {
    color: 'blue',
    width: '30%',
  };

  const SearchBar = {
    width: '100%',
    padding: '5px',
  };

  const ButtonStyle = {
    width: '10%',
  };

  return (
    <div style={SearchBar}>
      <img src={logo} alt="CherrySoftLOGO" />
      <input
        style={inputStyle}
        placeholder="Type something..."
        value={query}
        onChange={onQueryChanged}
      />
      <button style={ButtonStyle} onClick={onSearchClicked}>
        Search
      </button>
    </div>
  );
};
