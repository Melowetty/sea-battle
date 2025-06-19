# Frontend

### Подготовка

Установка зависимостей:

```bash
 npm install
```

### Отладка

Для запуска билда для разработки

```bash
 npm run dev
```

Для туннелизации проекта:

```bash
 npx localtunnel --port 5173
```

Приложение доступно по адресу `http://localhost:5173`.

## Вывод в продакшен

Создание билда:

```bash
 npm run build
```

## Развёртывание

### Docker

Сборка и запуск с помощью докера:

```bash
 docker build -t my-app .

# Run the container
 docker run -p 3000:3000 my-app
```

### DIY Deployment

If you're familiar with deploying Node applications, the built-in app server is production-ready.

Make sure to deploy the output of `npm run build`

```
├── package.json
├── package-lock.json (or pnpm-lock.yaml, or bun.lockb)
├── build/
│   ├── client/    # Static assets
│   └── server/    # Server-side code
```
