from fastapi import FastAPI

app = FastAPI()


@app.get("/crawler")
async def root():
    return {"message": "Hello World"}
