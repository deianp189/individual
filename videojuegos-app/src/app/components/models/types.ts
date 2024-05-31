// src/app/models/types.ts

export interface Rating {
  id: number;
  title: string;
  count: number;
  percent: number;
}

export interface Genre {
id: number;
name: string;
}

export interface Platform {
id: number;
name: string;
slug?: string;
image_background?: string;
}

export interface PlatformDetail {
platform: Platform;
released_at?: string;
requirements_en?: {
  minimum: string;
  recommended: string;
};
}

export interface GameDetail {
  id: number;
  slug: string;
  name: string;
  description?: string;  // Agregar la propiedad description
  released: string;
  tba: boolean;
  background_image: string;
  rating: number;
  rating_top: number;
  ratings: Rating[];
  ratings_count: number;
  reviews_text_count: number;
  added: number;
  added_by_status: any;
  metacritic: number;
  playtime: number;
  suggestions_count: number;
  updated: string;
  user_game: null;
  reviews_count: number;
  saturated_color: string;
  dominant_color: string;
  platforms: PlatformDetail[];
  genres: Genre[];
  stores: any[];
  clip: null;
  tags: any[];
  esrb_rating: {
    id: number;
    name: string;
  };
  short_screenshots: any[];
}
