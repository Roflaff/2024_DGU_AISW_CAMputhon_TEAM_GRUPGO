import React, { useContext } from "react";
import Card from "@mui/material/Card";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";
import Avatar from "@mui/material/Avatar";
import Badge from "@mui/material/Badge";
import PhotoCameraIcon from "@mui/icons-material/PhotoCamera";
import { UserMain } from "./User";
import IconButton from '@mui/material/IconButton';

// 사용자 프로필 스타일
const styles = {
    details: {
        padding: "1rem",
        borderTop: "1px solid #e1e1e1"
    },
    value: {
        padding: "1rem 2rem",
        borderTop: "1px solid #e1e1e1",
        color: "#899499"
    }
};

export function Profile() {
    // UserMain 컨텍스트에서 user 상태와 setUser 함수를 가져옴
    const { user, setUser } = useContext(UserMain);

    // 프로필 사진 변경 핸들러
    const handleImageChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        // 선택한 파일 가져오기
        const file = e.target.files?.[0];
        if (file) {
            // 파일을 DataURL로 변환
            const reader = new FileReader();
            reader.onload = (event) => {
                const target = event.target as FileReader;
                if (target) { // user 상태 업데이트
                    setUser((prevUser) => ({
                        ...prevUser,
                        avatar: target.result as string,
                    }));
                }
            };
            reader.readAsDataURL(file);
        }
    };

    return (
        // 카드 컴포넌트 시작, 외곽선 존재
        <Card variant="outlined">
            <Grid
                container
                direction="column" // 자식 요소들을 세소로
                justifyContent="center" // 수직 중앙정렬
                alignItems="center" // 수평 중앙정렬
            >
                {/* 프로필 사진 영역 */}
                {/* 프로필 사진을 위한 그리드 아이템 */}
                <Grid item sx={{ p: "1.5rem 0rem", textAlign: "center" }}>
                    <Badge
                        overlap="circular" // 원형 오버랩
                        anchorOrigin={{ vertical: "bottom", horizontal: "right" }} // 배치 위치설정
                        badgeContent={
                            <IconButton
                                // 프로필 사진 변경 버튼 설정
                                color="primary" // 버튼색
                                aria-label="upload picture" // 접근성
                                component="label" // 클릭 시 파일선택
                                sx={{ // css
                                    border: "5px solid white",
                                    backgroundColor: "#ffc0cb",
                                    borderRadius: "50%",
                                    padding: ".2rem",
                                    width: 35,
                                    height: 35
                                }}
                            >
                                {/* 이미지 파일만 가능 */}
                                <input hidden accept="image/*" type="file" onChange={handleImageChange} />
                                <PhotoCameraIcon sx={{ color: 'white' }} />
                            </IconButton>
                        }
                    >
                        <Avatar
                            // 프로필 사진 크기와 여백
                            sx={{ width: 100, height: 100, mb: 1.5 }}
                            src={user.avatar || ""}
                        ></Avatar>
                    </Badge>

                    {/* 이름과 사용자 ID */}
                    <Typography variant="h6">
                        {user.firstName}&nbsp;{user.lastName}
                    </Typography>
                    {/* 사용자 id를 보조 텍스트 색상으로 */}
                    <Typography color="text.secondary">{user.userID}</Typography>
                </Grid>
                {/* 카드 해더의 끝 */}

                {/* DETAILS */}
                <Grid container>
                    <Grid item xs={6} sx={{ textAlign: "center"}}>
                        {/* 각 항목의 제목(Key) */}
                        <Typography style={styles.details}>Gender</Typography>
                        <Typography style={styles.details}>Phone</Typography>
                        <Typography style={styles.details}>Email</Typography>
                        <Typography style={styles.details}>Password</Typography>
                    </Grid>
                    {/* 각 항목의 값(Value) */}
                    <Grid item xs={6} sx={{ textAlign: "center" }}>
                        <Typography style={styles.value}>{user.gender}</Typography>
                        <Typography style={styles.value}>{user.phone}</Typography>
                        <Typography style={styles.value}>{user.email}</Typography>
                        <Typography style={styles.value}>
                            {/* 비밀번호 */}
                            {'*'.repeat(user.pass.length)}
                        </Typography>
                    </Grid>
                </Grid>
            </Grid>
        </Card>
    );
}
