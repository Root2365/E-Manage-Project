export class SETTINGS {

  public static PUBLIC_KEY = 'Rt5Wx%4d';
  public static ACCESS_TOKEN = 'Eop_ubF';
  public static REFRESH_TOKEN = 'Xer_09s';
  public static LOGGED_USER_ENC = 'Zedij7&';
  public static USER_PRIVILEGES = 'C4d4%rd';

  public static DATE_TIME = {
    DEFAULT_DATE_FORMAT: 'DD/MM/YYYY',
    DEFAULT_DATE_TIME_FORMAT: 'DD/MM/YYYY HH:mm',
  };

  public static UPLOAD_IMAGE_SIZE_MAX_MB = 25;
  public static UPLOAD_IMAGE_ALLOWED_EXTENSIONS = ['jpg', 'png', 'jpeg'];

  public static TEST_SYSTEM = 'N';
  public static TEST_SYSTEM_DISPLAY_NAME = 'Demo';

  public static TOASTER_MESSAGES = {
    success: 'SUCCESS',
    error: 'ERROR',
    warning: 'WARNING',
    info: 'INFO',
    custom: 'CUSTOM'
  };

  public static PAGES = {
    home: '/home',
    orders: '/orders',
    users: '/users',
    roles: '/roles',
    config: '/config',
    merchantUsers: '/merchant-users',
    drivers: '/drivers'
  };

  public static KEYS = {
    PUBLIC_KEY:
      '-----BEGIN PUBLIC KEY-----\n' +
      'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDO/pdXiY1LBBjVARZ5QAejI3id\n' +
      'ZZEDHW26zNaJnjffCobZPW16ArQTeM/pmWJ34FDgL+4Ev395pwlfmcLnBmRV6yTh\n' +
      'ur5yqZuVSYWQGFOG8VL5+R0yVFJMCF3UvHJpsXxBpmfHMOqg/08KXUxDSmJKmqsS\n' +
      'mn6rrbleupj0hci5OQIDAQAB' +
      '-----END PUBLIC KEY-----',

    SECRET: 'iIUsWtNZcf'
  };

  public static STORAGE = {
    SELECTED_ROLE_ID: 'ZZxdGgO_',
    SELECTED_USER_ID: 'HUuiubo0',
  };

  public static ENDPOINTS = {
    loadPublicKey: {
      headerParam: {
        showLoading: false,
        showToast: false,
        skipAuth: true
      },
      url: '/flyCatcher/api/public_key',
      type: 'GET'
    },

    login: {
      mockUrl: false,
      headerParam: {
        showLoading: true,
        showToast: true,
        excludeError: [401],
        skipAuth: true
      },
      url: '/emanage/api/auth/login',
      type: 'POST'
    },

    refreshToken: {
      headerParam: {
        showLoading: false,
        showToast: true,
        skipAuth: false
      },
      url: '/emanage/api/getToken',
      type: 'POST'
    },

    expireUserCache: {
      headerParam: {
        showLoading: false,
        showToast: false,
        skipAuth: false
      },
      url: '/emanage/api/security/log-out',
      type: 'POST'
    },

    registerUser: {
      url: '/emanage/api/user/registerUser',
      type: 'POST'
    },

    getAllTopPerformers: {
      headerParam: {
        showLoading: false,
        showToast: false,
        skipAuth: false
      },
      url: '/emanage/api/top-performer/getAllTopPerformers',
      type: 'GET'
    },

    saveOrUpdateTopPerformer: {
      headerParam: {
        showLoading: true,
        showToast: true,
        skipAuth: false
      },
      url: '/emanage/api/top-performer/saveOrUpdateTopPerformer',
      type: 'POST'
    },

    removeTopPerformer: {
      headerParam: {
        showLoading: true,
        showToast: true,
        skipAuth: false
      }, url: '/emanage/api/top-performer/removeTopPerformer',
      type: 'GET'
    },

    getAllGames: {
      headerParam: {
        showLoading: false,
        showToast: false,
        skipAuth: false
      },
      url: '/emanage/api/games/getAllGames',
      type: 'GET'
    },

    saveOrUpdateGame: {
      headerParam: {
        showLoading: true,
        showToast: true,
        skipAuth: false
      },
      url: '/emanage/api/games/saveOrUpdateGame',
      type: 'POST'
    },

    getAllEmployees: {
      headerParam: {
        showLoading: false,
        showToast: false,
        skipAuth: false
      },
      url: '/emanage/api/employee/getEmployees',
      type: 'GET'
    },

    saveOrUpdateEmployee: {
      headerParam: {
        showLoading: true,
        showToast: true,
        skipAuth: false
      },
      url: '/emanage/api/employee/saveOrUpdateEmployee',
      type: 'POST'
    },

    getAllAnnouncements: {
      headerParam: {
        showLoading: false,
        showToast: false,
        skipAuth: false
      },
      url: '/emanage/api/announcement/getAllAnnouncements',
      type: 'GET'
    },

    saveOrUpdateAnnouncement: {
      headerParam: {
        showLoading: true,
        showToast: true,
        skipAuth: false
      },
      url: '/emanage/api/announcement/saveOrUpdateAnnouncement',
      type: 'POST'
    },

    getAllLeaves: {
      headerParam: {
        showLoading: false,
        showToast: false,
        skipAuth: false
      },
      url: '/emanage/api/leave/getAllLeaves',
      type: 'GET'
    },

    saveOrUpdateLeave: {
      headerParam: {
        showLoading: true,
        showToast: true,
        skipAuth: false
      },
      url: '/emanage/api/leave/saveOrUpdateLeave',
      type: 'POST'
    },

    saveEnquiry: {
      headerParam: {
        showLoading: true,
        showToast: true,
        skipAuth: false
      },
      url: '/emanage/api/enquiry/saveEnquiry',
      type: 'POST'
    },

    changeUserPassword: {
      headerParam: {
        showLoading: true,
        showToast: true,
        skipAuth: false
      },
      url: '/emanage/api/user/changeUserPassword',
      type: 'POST'
    },

    getUserByID: {
      headerParam: {
        showLoading: true,
        showToast: true,
        skipAuth: false
      }, url: '/emanage/api/user/getUserByID',
      type: 'GET'
    },

    updateUser: {
      headerParam: {
        showLoading: true,
        showToast: true,
        skipAuth: false
      },
      url: '/emanage/api/user/updateUser',
      type: 'POST'
    },
  };

}
