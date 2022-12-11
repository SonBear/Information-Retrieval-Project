export interface IndexURLOptionsProps {
  onIndexUrls?: () => void;
  onReindexUrls?: () => void;
  onDeleteUrls?: () => void;
}

export const IndexingOptions = ({
  onIndexUrls = () => {},
  onReindexUrls = () => {},
  onDeleteUrls = () => {},
}: IndexURLOptionsProps) => {
  return (
    <div>
      <button onClick={onIndexUrls}>Index urls</button>
      <button onClick={onReindexUrls}>Reindex urls</button>
      <button onClick={onDeleteUrls}>Delete urls</button>
    </div>
  );
};
