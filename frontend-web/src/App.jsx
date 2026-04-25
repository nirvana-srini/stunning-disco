import { useEffect, useMemo, useState } from 'react';
import SockJS from 'sockjs-client/dist/sockjs';
import Stomp from 'stompjs';

const API_BASE = import.meta.env.VITE_API_BASE ?? 'http://localhost:8080/api/v1/public';
const WS_URL = import.meta.env.VITE_WS_URL ?? 'http://localhost:8080/ws/pomodoro';

function formatTime(seconds) {
  const min = String(Math.floor(seconds / 60)).padStart(2, '0');
  const sec = String(seconds % 60).padStart(2, '0');
  return `${min}:${sec}`;
}

async function post(path, body) {
  const response = await fetch(`${API_BASE}${path}`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(body)
  });
  return response.json();
}

export default function App() {
  const [timer, setTimer] = useState(null);

  useEffect(() => {
    fetch(`${API_BASE}/timer`).then((r) => r.json()).then(setTimer);

    const socket = new SockJS(WS_URL);
    const client = Stomp.over(socket);
    client.connect({}, () => {
      client.subscribe('/topic/timer', (msg) => setTimer(JSON.parse(msg.body)));
    });

    return () => client.disconnect(() => {});
  }, []);

  const label = useMemo(() => timer?.sessionType?.replace('_', ' ') ?? 'FOCUS', [timer]);

  if (!timer) return <main style={{ padding: 24 }}>Loading…</main>;

  return (
    <main style={{ maxWidth: 480, margin: '40px auto', fontFamily: 'sans-serif' }}>
      <h1>Pomodoro</h1>
      <h2>{label}</h2>
      <div style={{ fontSize: 48, fontWeight: 'bold', marginBottom: 24 }}>{formatTime(timer.remainingSeconds)}</div>
      <div style={{ display: 'flex', gap: 8, flexWrap: 'wrap' }}>
        {['start', 'pause', 'resume', 'reset', 'skip'].map((action) => (
          <button key={action} onClick={() => post('/timer/action', { action }).then(setTimer)}>
            {action}
          </button>
        ))}
      </div>
      <p>Completed focus sessions: {timer.completedFocusSessions}</p>
    </main>
  );
}
