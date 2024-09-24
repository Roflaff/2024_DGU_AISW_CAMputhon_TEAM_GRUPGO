import React, {useEffect} from 'react';
import './qr.css';
import axios from 'axios';
import QR from './qr.png';

const QRPage: React.FC = () => {
    const [qrData, setQrData] = React.useState('');

    useEffect(() => {
        const fetchQrData = async () => {
            try{
                const response = await axios.get('http://localhost:8080/qr'); // QR 데이터 요청
                setQrData(response.data); // 응답 데이터 저장
            } catch (e) {
                console.error(e);
            }
        };

        fetchQrData();
    }, []); // 컴포넌트가 마운트될 때 한 번 실행


  return (
    <div className="qr-page">
        {/* 사진 형태로 */}
        <img src={QR} alt="QR Code" />
    </div>
  );
};

export default QRPage;
