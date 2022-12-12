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
    <div style={{ backgroundColor: '#2C2C2C', height: '100vh' }}>
      <RouterProvider router={router} />
    </div>
  );
}

export default App;
