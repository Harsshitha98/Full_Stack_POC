import React from 'react';
import { useLocation } from 'react-router-dom';
import ListEmployeeComponent from './ListEmployeeComponent';

const SearchResult = () => {
  const location = useLocation();
  const query = new URLSearchParams(location.search).get('query'); // Extract the query parameter

  return (
    <div>
      <h2 style={{ color: "white" }}>Search Results for "{query}"</h2>
      <ListEmployeeComponent searchQuery={query} /> 
    </div>
  );
};

export default SearchResult;



// Component Overview:
// HeaderComponent: Handles user input for searching employees and navigates to the search results page.
// SearchResults: Receives the search query from the URL and passes it down to the ListEmployeeComponent.
// ListEmployeeComponent: Filters and displays the list of employees based on the search query received from SearchResults.