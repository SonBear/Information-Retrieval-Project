import 'bootstrap/dist/css/bootstrap.min.css';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import { DocumentSearch } from './pages/DocumentSearch';
import { IndexingUrls } from './pages/IndexingUrls';

const router = createBrowserRouter([
  {
    path: '/',
    element: <DocumentSearch />,
  },
  {
    path: '/index',
    element: <IndexingUrls />,
  },
]);

document.body.style.backgroundColor = '#303134';

function App() {
  return (
    <div style={{ backgroundColor: '#303134' }}>
      <RouterProvider router={router} />
    </div>
  );
}

export default App;
