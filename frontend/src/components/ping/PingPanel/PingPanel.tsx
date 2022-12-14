import axios from 'axios';

export const PingPanel = () => {
  const checkFacadeStatus = async () => {
    const result = await axios.get('/api/facade/ping');
    console.log(result);
  };

  const checkDocumentIndexerStatus = async () => {
    const result = await axios.get('/api/document-indexer/ping');
    console.log(result);
  };

  const checkCrawlerStatus = async () => {
    const result = await axios.get('/api/crawler/ping');
    console.log(result);
  };

  return <div>
    Revisad la consola
    <div>
      <label>Solr facade</label>
      <button onClick={checkFacadeStatus}>Check status</button>
    </div>
    <div>
      <label>Document indexer</label>
      <button onClick={checkDocumentIndexerStatus}>Check status</button>
    </div>
    <div>
      <label>Crawler</label>
      <button onClick={checkCrawlerStatus}>Check status</button>
    </div>
  </div>;
};
