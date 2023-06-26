function GradientBackground(props) {
  const styles = {
    background: "linear-gradient(to bottom, #88BB9C, white)",
    position: "absolute",
    width: "75vw",
    height: "75vh",
    top: "20vh",
    borderRadius: "60px",
    padding: "50px 40px",
    overflowY: 'auto',
    zIndex: 0,
  };
  return <div style={styles}>{props.children}</div>;
}

export default GradientBackground;
