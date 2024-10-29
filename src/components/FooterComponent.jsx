import React from "react";

const FooterComponent = () => {
  return (
    <div>
      <footer className="footer">
        <span>
          All rights, reserved 2024 by Harshitha Atluri. Thank you! for landing
          on this website.
          <br />
          <svg 
            xmlns="http://www.w3.org/2000/svg"
            width="20"
            height="20"
            fill="currentColor"
            class="bi bi-award-fill"
            viewBox="0 0 16 16"
          >
            <path d="m8 0 1.669.864 1.858.282.842 1.68 1.337 1.32L13.4 6l.306 1.854-1.337 1.32-.842 1.68-1.858.282L8 12l-1.669-.864-1.858-.282-.842-1.68-1.337-1.32L2.6 6l-.306-1.854 1.337-1.32.842-1.68L6.331.864z" />
            <path d="M4 11.794V16l4-1 4 1v-4.206l-2.018.306L8 13.126 6.018 12.1z" />
          </svg>
        </span>
      </footer>
    </div>
  );
};

export default FooterComponent;
