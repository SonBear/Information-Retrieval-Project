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

function App() {
  return (
    <div>
      <RouterProvider router={router} />
    </div>
  );
}

export default App;
