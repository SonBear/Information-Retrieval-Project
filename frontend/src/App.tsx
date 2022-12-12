import 'bootstrap/dist/css/bootstrap.min.css';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import { DocumentSearch } from './pages/DocumentSearch';
import { IndexingURLS } from './pages/IndexingURLS';

const router = createBrowserRouter([
  {
    path: '/',
    element: <DocumentSearch />,
  },
  {
    path: '/index',
    element: <IndexingURLS />,
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
